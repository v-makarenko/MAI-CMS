/**
 * Created by VMakarenko on 4/12/2015.
 */
angular.module("app").service('EventsService',
    function ($http) {
        var url = "api/private/events/";

        this.getAll = function () {
            return $http.get(url);
        };
        this.getAllPublic = function () {
            return $http.get("api/events/");
        };

        this.get = function (id) {
            return $http.get(url + id);
        };

        this.delete = function (id) {
            return $http.delete(url + id);
        };

        this.save = function (event) {
            if (event.id) {
                return $http.post(url, event);
            } else {
                return $http.put(url, event);
            }
        };

        this.setPresence = function (present, userId, eventId) {
            return $http.post(url + 'setPresence', {
                present: present,
                userId: userId,
                eventId: eventId
            });
        }

    }
)
;