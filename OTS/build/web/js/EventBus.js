

var OTS = OTS || {};
///<summary>Applying Publish/Subscribe pattern for view communication</summary>
OTS.eventBus = (function ($ko) {
    var bus = new ko.subscribable();
    ///<param name="topic">Subcribe for event</param>
    ///<param name="callbackTarget"></param>
    ///<param name="callback"></param>
    var subcribe = function (topic,callbackTarget,callback) {
        bus.subscribe(callback, callbackTarget, topic);
     
    };
    ///<param name="Event Topic">Notify event</param>
    ///<param name="Event Topic"></param>
    var notify = function (topic,data) {
        bus.notifySubscribers(data, topic);
    };

    return {
        Subscribe: subcribe,
        Notify: notify
    };
}(ko));