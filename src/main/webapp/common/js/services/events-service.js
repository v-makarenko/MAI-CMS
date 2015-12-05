/**
 * Created by VMakarenko on 4/12/2015.
 */
angular.module("app").service('EventsService',
    function ($http) {
        var privateUrl = "api/private/events/";
        var publicUrl = "api/public/events/";

        this.getAll = function () {
            return $http.get(privateUrl);
        };
        this.getAllPublic = function () {
            return $http.get("api/events/");
        };

        this.get = function (id) {
            return $http.get(privateUrl + id);
        };

        this.delete = function (id) {
            return $http.delete(privateUrl + id);
        };

        this.save = function (event) {
            if (event.id) {
                return $http.post(privateUrl, event);
            } else {
                return $http.put(privateUrl, event);
            }
        };

        this.setPresence = function (present, userId, eventId) {
            return $http.post(privateUrl + 'setPresence', {
                present: present,
                userId: userId,
                eventId: eventId
            });
        };

        this.techPeopleSave = function(item) {
            return $http.post(privateUrl + 'techPeopleSave', item);
        };

        this.getArchive = function() {
            return $http.get(publicUrl + 'archive');
        };

        this.getCurrent = function() {
            return $http.get(publicUrl + 'current');
        }


    }
)
;