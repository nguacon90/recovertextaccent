var Application = function (options) {
	this.originParagraph = options.originParagraph;
	this.inputParagraph = options.inputParagraph;
	this.outputParagraph = options.outputParagraph;
	this.urlRestService = options.urlRestService;
	this.percentElm = options.percent;
	this.elements= {
		removeAccentBtn : options.removeAccentBtn,
		recoverAccentBtn: options.recoverAccentBtn
	};
	this.isProcessing = false;
	this.loadingElm = options.loadingElm;
	this.regex = /[`~!@#$%^&*()_|+\-=?;:'"“,.<>\{\}\[\]\\\/]/gi;
	this.htmlRegex = /<\/?[^>]+(>|$)/g;
};

Application.prototype = {
	init: function() {
		this.listenFromView();
	},
	
	listenFromView: function() {
		var self = this;
		this.elements.removeAccentBtn.on("click", function(){
			if(self.isProcessing) {
				return false;
			}
			self.isProcessing = true;
			self.showLoading();
			self.processData({
				url : "rest/service/removeAccent",
				"input": self.originParagraph.val()
			}).done(function(data){
				self.inputParagraph.html(data.model);
				self.isProcessing = false;
				self.hideLoading();
			}).fail(function(){
				self.isProcessing = false;
				self.hideLoading();
			});
		});
		
		this.elements.recoverAccentBtn.on("click", function(){
			if(self.isProcessing) {
				return false;
			}
			self.isProcessing = true;
			self.showLoading();
			self.processData({
				url : "rest/service/recoverAccent",
				"input": self.inputParagraph.val()
			}).done(function(data){
				self.outputParagraph.html(data.model);
				self.computePercent(data.model);
				
				self.isProcessing = false;
				self.hideLoading();
			}).fail(function(){
				self.isProcessing = false;
				self.hideLoading();
			});
		});
	},
	
	computePercent: function(recoveredResult) {
		var oldText = this.originParagraph.val().replace(this.regex, ' ').replace(/\s{2,}/g, ' ').replace(/(\r\n|\n|\r)/gm, ' ');
		var olds = oldText.split(" ");
		var recoveredText = recoveredResult.replace(this.regex, ' ').replace(/\s{2,}/g, ' ');
		var recovers = recoveredText.split(" ");
		var countCorrect = 0;
		for(var i=0; i<olds.length; i++) {
			console.log(olds[i], recovers[i]);
			if(olds[i] === recovers[i]) {
				countCorrect++;
			}
		}
		
		console.log(countCorrect);
		console.log(olds.length);
		console.log(recovers.length);
		var percent = Math.round(countCorrect/olds.length * 10000) / 100 + '%';
		this.percentElm.html(percent) ;
	},
	
	showLoading: function() {
		this.loadingElm.show();
	},
	
	hideLoading: function() {
		this.loadingElm.hide();
	},
	
	processData: function(options) {
		var def = $.Deferred();
		$.ajax({
			url : options.url,
			type: "POST",
			data: {
				"input": options.input
			},
			success: function(data) {
				var hasError = data.hasError;
				if(!hasError) {
					def.resolve(data);
				} else {
					def.reject();
				}
			},
			fail: function(data) {
				def.reject();
			}
		});
		return def.promise();
	}
};