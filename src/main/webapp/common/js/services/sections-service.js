/**
 * Created by VMakarenko on 4/12/2015.
 */
angular.module("app").service('SectionsService',
    function ($http) {
        var url = "api/private/sections/";

        this.save = function (wplace) {
            return $http.put(url, wplace)
        };

        this.find = function (id) {
            return $http.get(url + id);
        };

        this.getAll = function (eventId) {
            return $http.get(url + 'getAll', {params: {eventId: eventId}});
        };
        this.send = function (fromId, toId, messageText, attach) {
            return $http.post(url + 'send', {fromId: fromId, toId: toId, messageText: messageText, attach: attach});
        };

    }
)
;