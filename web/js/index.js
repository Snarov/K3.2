var LoginModalController = {
	tabsElementName: ".logmod__tabs li",
	tabElementName: ".logmod__tab",
	inputElementsName: ".logmod__form .input",
	hidePasswordName: ".hide-password",
	inputElements: null,
	tabsElement: null,
	tabElement: null,
	hidePassword: null,
	activeTab: null,
	

	getCookie: function (name) {
		var cookie = " " + document.cookie;
		var search = " " + name + "=";
		var setStr = null;
		var offset = 0;
		var end = 0;
		if (cookie.length > 0) {
			offset = cookie.indexOf(search);
			if (offset != -1) {
				offset += search.length;
				end = cookie.indexOf(";", offset)
				if (end == -1) {
					end = cookie.length;
				}
				setStr = unescape(cookie.substring(offset, end));
			}
		}
		return(setStr);
	},
	
	setCookie: function  (name, value, expires, path, domain, secure) {
      document.cookie = name + "=" + escape(value) +
        ((expires) ? "; expires=" + expires : "") +
        ((path) ? "; path=" + path : "") +
        ((domain) ? "; domain=" + domain : "") +
        ((secure) ? "; secure" : "");
},
	
	findElements: function () {
		var base = this;

		base.tabsElement = $(base.tabsElementName);
		base.tabElement = $(base.tabElementName);
		base.inputElements = $(base.inputElementsName);
		base.hidePassword = $(base.hidePasswordName);

		return base;
	},
	
	setState: function (state) {
		var base = this,
				elem = null;

		
		if (base.tabsElement) {
			elem = $(base.tabsElement[+state]);
			elem.addClass("current");
			$("." + elem.attr("data-tabtar")).addClass("show");
		}

		return base;
	},
	
	getActiveTab: function () {
		var base = this;

		base.tabsElement.each(function (i, el) {
			if ($(el).hasClass("current")) {
				base.activeTab = $(el);
			}
		});

		return base;
	},
	
	addClickEvents: function () {
		var base = this;

		base.hidePassword.on("click", function (e) {
			var $this = $(this),
					$pwInput = $this.prev("input");

			if ($pwInput.attr("type") == "password") {
				$pwInput.attr("type", "text");
				$this.text("Скрыть");
			} else {
				$pwInput.attr("type", "password");
				$this.text("Показать");
			}
		});

		base.tabsElement.on("click", function (e) {
			var targetTab = $(this).attr("data-tabtar");

			e.preventDefault();
			base.activeTab.removeClass("current");
			base.activeTab = $(this);
			base.activeTab.addClass("current");

			base.tabElement.each(function (i, el) {
				el = $(el);
				el.removeClass("show");
				if (el.hasClass(targetTab)) {
					base.setCookie("tabSelection", i);
					el.addClass("show");
				}
			});
		});

		base.inputElements.find("label").on("click", function (e) {
			var $this = $(this),
					$input = $this.next("input");

			$input.focus();
		});

		return base;
	},
	
	initialize: function () {
		var base = this;
		var tabSelection = base.getCookie("tabSelection");
		if (!tabSelection){
			tabSelection = true;
		}
		tabSelection = !+tabSelection;

		base.findElements().setState(tabSelection).getActiveTab().addClickEvents();
	}
};

$(document).ready(function () {
	
	LoginModalController.initialize();
});