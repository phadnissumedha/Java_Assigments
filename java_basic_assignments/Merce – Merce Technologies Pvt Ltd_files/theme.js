jQuery(document).ready(function() { 

	"use strict";

	/******
	GLOBAL SEARCH: SHOW/HIDE
	********/
    jQuery(".site-header-search-icon").on("click",function(e) { 
       jQuery(".search-overlay.open").show(500);
    });
    jQuery(".search-overlay-close").on("click",function(e) { 
       jQuery(".search-overlay.open").hide(500);
    });
	
	
	/******
	FLEXSLIDER LOAD
	********/
	jQuery('.flexslider').flexslider({ 
		controlNav: true,
		slideshowSpeed: 7000,
		animation: "fade",
		prevText: "",      
		nextText: "",  
		touch: true, 
		directionNav: true, 
	});
	
	
	/******
	BLOG FLEXSLIDER LOAD
	********/
	jQuery('.blog-flexslider').flexslider({ 
		controlNav: true,
		slideshowSpeed: 7000,
		animation: "fade",
		prevText: "",      
		nextText: "",  
		touch: true, 
		directionNav: false, 
	});
	
	/******
	VIDEO PLAY
	********/
	jQuery('.theme-video-play').magnificPopup({
	  disableOn: 700,
	  type: 'iframe',
	  mainClass: 'mfp-fade',
	  removalDelay: 160,
	  preloader: false,
	  fixedContentPos: false
	});
	
	
	/******
	HB MENU
	********/
	jQuery('.hamburger-menu').on('click', function(e) {  
		 if (jQuery(".hamburger-menu").hasClass("menu-open")) {
			  jQuery('.hamburger-menu').removeClass( "menu-open" ); 
			  jQuery('.site-header .site-header-category-links').hide(); 
			  jQuery(window).trigger("resize");
		 } else {
			 jQuery('.hamburger-menu').addClass( "menu-open" );  
			 jQuery('.site-header .site-header-category-links').show(); 
			 jQuery(window).trigger("resize");
		 }
	});
	
	
	/******
	MOBILE NAV
	********/
	jQuery('.site-header i.navbar-toggle.collapsed').on("click",function(e) {  
       jQuery('.mobile-menu-holder').slideToggle('slow');  
        e.preventDefault();  
    });
	
	
	/******
	VIEW MORE BRANDS
	********/
	jQuery('p.onclick-display-more-hidden-brands').on("click",function(e) {  
		jQuery('.display-more-hidden-brands').show('slow');
		jQuery("p.onclick-display-more-hidden-brands").hide(); 
		jQuery("p.onclick-display-less-brands-hide").show(); 
		return false;
    });
	jQuery('p.onclick-display-less-brands-hide').on("click",function(e) { 
		jQuery('.display-more-hidden-brands').hide('slow');
		jQuery("p.onclick-display-more-hidden-brands").show(); 
		jQuery("p.onclick-display-less-brands-hide").hide();  
		return false;
    });
	
	
	/******
	TABS :: Brand+Type
	********/
	jQuery('li.tab-brand-type').on("click",function(e) {
		jQuery('.all-brand-cars').show('slow');
		jQuery('.all-body-type-cars').hide();
		jQuery('li.tab-body-type').removeClass('active');
		jQuery('li.tab-brand-type').addClass('active');
		return false;
    });
	jQuery('li.tab-body-type').on("click",function(e) {  
		jQuery('.all-brand-cars').hide();
		jQuery('.all-body-type-cars').show('slow');
		jQuery('li.tab-brand-type').removeClass('active'); 
		jQuery('li.tab-body-type').addClass('active');
		return false;
    });

	
	/******
	PAGE :: SLIDERS
	********/
	jQuery('.owl-image-slider').owlCarousel({
		items : 4,
		navigation :false,
		pagination : true,
		responsive: true,
		lazyLoad : true,
		itemsDesktop : [1200,3],
		itemsDesktopSmall : [1000,2],
		itemsTablet: [768,2],
		itemsMobile : [479,1],
	});
	
	
	/******
	PAGE :: SCROLL UP
	********/
	if ( (jQuery(window).width()>=767) ) {
		// hide #back-top first
		jQuery("#scrollbkToTop").hide();
		jQuery( "body" ).append( "<p id=\"scrollbkToTop\" style=\"display: none;\"><a href=\"#top\"><span> <i class=\'"+go_up_icon+" footer-go-uplink\'></i></span></a></p>" );
		
		// fade in #back-top
		jQuery(function () {
			jQuery(window).on("scroll",function() {
				if (jQuery(this).scrollTop() > 150) {
					jQuery('#scrollbkToTop').fadeIn();
				} else {
					jQuery('#scrollbkToTop').fadeOut();
				}
			});
		
			// scroll body to 0px on click
			jQuery('#scrollbkToTop a').on("click",function() { 
				jQuery('body,html').animate({
					scrollTop: 0
				}, 800);
				return false;
			});
		});
	}
	
	
	/******
	ON SCROLL STICKY MENU BAR
	********/
	if( sticky_menu == 1 ) {
		var previousScroll = 0;	// after scroll to top
		jQuery(window).on("scroll",function() {
			var $mainClass = jQuery(".site-header"), $body = jQuery("body");	
			var $original_html_height = jQuery( document ).height();
			if ((jQuery("html nav").hasClass("site-header"))){
				if ((jQuery(window).scrollTop() > 25)&& (jQuery(window).width()>=1100) ) {  
					if($mainClass.hasClass("after-scroll-wrap"))
					return false;
					
					$body.addClass("search-active-sticky").css("padding-top", 0);
					$mainClass.addClass("after-scroll-wrap");
					jQuery(window).trigger("resize").trigger("scroll");
				} else {
					if($body.hasClass("search-active-sticky")) {
						$body.removeClass("search-active-sticky").css("padding-top", 0);
						$mainClass.removeClass("after-scroll-wrap");
						jQuery(window).trigger("resize").trigger("scroll");
					}
				}
			}
		});
	}
	

	/******
	AUDIO PLAYER FOR BLOG LAYOUT
	********/	
	jQuery('audio.blog_audio').mediaelementplayer({
		audioWidth: '100%'
	});
	
	
	/******
	PORTFOLIO TESTIMONIAL SLIDER
	********/
	jQuery(".bind-single-custom-slider").owlCarousel({
	  navigation : false, // Show next and prev buttons
	  slideSpeed : 300,
	  paginationSpeed : 400,
	  singleItem : true
	});
	
	/******
	SEARCH
	********/
	jQuery( ".form-group input.header-search" ).mousedown(function() { 
		jQuery('.form-group input.header-search').attr('placeholder',filed_searchmsg);
	});
	jQuery( ".form-group input.header-search"  ).focusout(function() { 
	  var old_place_holder = jQuery( "#oldplacvalue" ).val();
	  jQuery('.form-group input.header-search').attr('placeholder', old_place_holder);
	});
	
	/******
	LIVE SEARCH
	********/
	if( live_search_active ==  1 ) {
		jQuery('#searchform #s').liveSearch({
				url: ''+live_search_url+'?ajax=on&s='
		});
	}

	
	/******
	VC COUNTER
	********/
	jQuery('.counter-main-div').appear(function() { 
		jQuery('.timer').countTo();
		jQuery(window).trigger("resize");
	},{accX: 90, accY: 100});
	
	
	/******
	LOAD MORE
	********/
	var loada = 1;
	jQuery(".ajax_load_more_post a").on("click", function(b) {
		 b.preventDefault();
		 var c = jQuery(this).attr("href"),
		     d = ".projects_holder",
			 g = jQuery(".projects_holder .filler").length,
			 e = ".portfolio_paging .ajax_load_more_post a",
			 f = jQuery(e).attr("href"),
		     h = jQuery(".portfolio_paging"),
             i = jQuery(".portfolio_paging_loading");
			 h.hide(), i.show(), jQuery.get(c + "", function(b) {
				 jQuery(".projects_holder .filler").slice(-g).remove();
				 var c = jQuery(d, b).wrapInner("").html();
				 f = jQuery(e, b).attr("href"), jQuery(d, b).waitForImages(function() {  
					jQuery("div.portfolio-section-records:last").after(c);
					jQuery(".projects_holder").isotope("reloadItems").isotope();
					jQuery(".ajax_load_more_post").attr("rel") > loada ? jQuery(".ajax_load_more_post a").attr("href", f) : jQuery(".portfolio_paging").remove();
					h.show(); i.hide();
					jQuery(window).trigger("resize");
				 })
			 }), loada++
	})
	
	/******
	LOAD MORE - POST
	********/
	var loadapost = 1;
	jQuery(".ajax_load_more_post_record a").on("click", function(b) {
		 b.preventDefault();
		 var c = jQuery(this).attr("href"),
		     d = ".vc_theme_blog_post_holder",
			 g = jQuery(".vc_theme_blog_post_holder .filler").length,
			 e = ".blog_vc_paging .ajax_load_more_post_record a",
			 f = jQuery(e).attr("href"),
		     h = jQuery(".blog_vc_paging"),
             i = jQuery(".blog_vc_paging_loading");
			 h.hide(), i.show(), jQuery.get(c + "", function(b) {
				 jQuery(".vc_theme_blog_post_holder .filler").slice(-g).remove();
				 var c = jQuery(d, b).wrapInner("").html();
				 f = jQuery(e, b).attr("href"), jQuery(d, b).waitForImages(function() {  
					jQuery("div.blog-section-records:last").after(c);
					jQuery(".vc_theme_blog_post_holder").isotope("reloadItems").isotope();
					jQuery(".ajax_load_more_post_record").attr("rel") > loadapost ? jQuery(".ajax_load_more_post_record a").attr("href", f) : jQuery(".blog_vc_paging").remove();
					h.show(); i.hide();
					jQuery(window).trigger("resize");
				 })
			 }), loadapost++
	})
	
	/******
	FAQ
	********/
	if ( faq_search_id != '' ){ 
		 jQuery('.collapsible-panels.theme-faq-cat-pg div').hide(); 
		 jQuery('#'+faq_search_id ).addClass( "active" ); 
		 jQuery('#'+faq_search_id+' .entry-content').show();
	} else {  
		jQuery('.collapsible-panels.theme-faq-cat-pg div').hide();
	}
	jQuery('.collapsible-panels.theme-faq-cat-pg h5.title-faq-cat').on("click",function(e) { 
        jQuery(this).next('.collapsible-panels div').slideToggle('slow');  
        jQuery(this).parent().toggleClass('active');  
		jQuery('.entry-content .social-box').show();
        e.preventDefault();  
    });
	
	
	/******
	WOO PLUS/MINUS
	********/
	jQuery('.quantity.woo-add-plus-minus').on('click', '.plus', function(e) {
		var input = jQuery(this).prev('input.qty');
		var val = parseInt(input.val());
		input.val( val+1 ).change();
	});

	jQuery('.quantity.woo-add-plus-minus').on('click', '.minus', 
		function(e) {
		var input = jQuery(this).next('input.qty');
		var val = parseInt(input.val());
		if (val > 0) {
			input.val( val-1 ).change();
		} 
	});
	
	
	/******
	SMOOTH SCROLL
	********/
	jQuery('a[href*="#"]')
	.not('[href="#"]')
	.not('[href="#0"]')
	.on("click", function(event) {
	// On-page links
	if (
	  location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '') 
	  && 
	  location.hostname == this.hostname
	) {
	  // Figure out element to scroll to
	  var target = jQuery(this.hash);
	  target = target.length ? target : jQuery('[name=' + this.hash.slice(1) + ']');
	  // Does a scroll target exist?
	  if (target.length) {
		// Only prevent default if animation is actually gonna happen
		event.preventDefault();
		jQuery('html, body').animate({
		  scrollTop: target.offset().top
		}, 1000, function() {
		  // Callback after animation
		  // Must change focus!
		  var $target = jQuery(target);
		  //$target.focus();
		  if ($target.is(":focus")) { // Checking if the target was focused
			return false;
		  } else {
			$target.attr('tabindex','-1'); // Adding tabindex for elements not focusable
			//$target.focus(); // Set focus again
		  };
		});
	  }
	}
	});
	
	
});

/******
	GLOBAL MENU POSITION
********/
jQuery(window).load(function() {
    "use strict";
    initDropDownMenu();
	
	/******
	MASONRY 2,3,4
	********/
	jQuery('.masonry-grid, .masonry-grid-without-sidebar').masonry({
	  itemSelector: '.masonry-item',
	  columnWidth: '.col-md-3, .col-md-4, .col-md-6',
	  percentPosition: true
	});
	
	/******
	WOO MASONRY 2,3,4
	********/
	var columnCount = 3;
	var gutter = 10;
	jQuery('.woo-masonry-item').width(function() {
        return ((((jQuery('.woo-masonry-grid').width() - ((columnCount*gutter) - gutter)) / columnCount) / jQuery('.woo-masonry-grid').width()) * 100)+'%';
    });
    
    jQuery( window ).resize(function() {
		if (jQuery(window).width() < 767) {
			jQuery('.woo-masonry-item').width(function() {
				return '100%';
			});
		} else {
			jQuery('.woo-masonry-item').width(function() {
				return ((((jQuery('.woo-masonry-grid').width() - ((columnCount*gutter) - gutter)) / columnCount) / jQuery('.woo-masonry-grid').width()) * 100)+'%';
			});
		}
    });
	
	jQuery('.woo-masonry-grid').masonry({
	  itemSelector: '.woo-masonry-item',
	  columnWidth: '.col-md-3, .col-md-4, .col-md-6',
	  percentPosition: true,
	  gutter: gutter,
	});
	
	
	/******
	PORTFOLIO :: MASONRY FILTER
	********/
	var $masnorygrid = jQuery('.isotope-container-masnory').isotope({
		itemSelector: '.element-item',
	});
	jQuery('.portfolio-sorting-section').on( 'click', 'li', function() {  
		var $filter  = jQuery(this),
			selector = $filter.attr('data-filter-masnory');
		
		$masnorygrid.imagesLoaded( function() {		
			$masnorygrid.isotope({
				filter: selector
			});
		});
		jQuery(this).addClass('selected').siblings().removeClass('selected');
		jQuery(window).trigger("resize");
	});
	
	/******
	PORTFOLIO :: GRID FILTER
	********/
	var $grid = jQuery('.isotope-container-grid').isotope({
		itemSelector: '.element-item',
		layoutMode: 'fitRows'
	});
	jQuery('.portfolio-sorting-fitrows-section').on( 'click', 'li', function() {  
		var $filter  = jQuery(this),
			selector = $filter.attr('data-filter-masnory');
		
		$grid.imagesLoaded( function() {	
			$grid.isotope({
				filter: selector
			});
		});
		jQuery(this).addClass('selected').siblings().removeClass('selected');
		jQuery(window).trigger("resize");
	});
	
	/******
	VC :: Logo Slider
	********/
	jQuery('.logo-slider').flexslider({
		animation: "slide",
		animationLoop: false,
		itemWidth: 190,
		itemMargin: 5,
		minItems: 2,
		maxItems: 6,
		touch: true, 
	});
	
	
	/******
	VC :: Testimonial Slider
	********/
	jQuery('.testimonial-slider').flexslider({
		animation: "slide",
		animationLoop: false,
		itemWidth: 190,
		itemMargin: 5,
		minItems: 1,
		maxItems: 1,
		touch: true, 
	});
	
		
});

function initDropDownMenu() { 
    "use strict";
	jQuery(".site-header .site-header-category-links li").on('mouseenter mouseleave', function (e) {
        if (jQuery('ul', this).length) { 
            var elm = jQuery('ul:first', this);
            var off = elm.offset();
            var l = off.left;
            var w = elm.width();
            var docH = jQuery(".site-header").height();
            var docW = jQuery(".site-header").width();
            var isEntirelyVisible = (l + w <= docW);
            if (!isEntirelyVisible) {
                jQuery(this).addClass('menu-edge');
            } else {
                jQuery(this).removeClass('menu-edge');
            }
        }
    });
}


/******
	WINDOW RESIZE
********/
jQuery(window).resize(function() {
  // mobile nav fix
  if (jQuery(this).width() > 991) { 
    jQuery(".mobile-menu-holder").hide();
  }
});