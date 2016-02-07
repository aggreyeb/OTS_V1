
(function ($) {
    if (ko && ko.bindingHandlers) {
        ko.bindingHandlers.bsPopover = {
            init: function (element, valueAccessor) {
                var val = ko.utils.unwrapObservable(valueAccessor());
                var options = { title: val.title,
                    animation: val.animation,
                    placement: val.placement,
                    trigger: val.trigger,
                    delay: val.delay,
                    //can grab html content from a target option
                    content: ($(val.target).html() != null) ? $(val.target).html() : val.content
                };
                $(element).popover(options);
            }
        };
    };
})(jQuery);



