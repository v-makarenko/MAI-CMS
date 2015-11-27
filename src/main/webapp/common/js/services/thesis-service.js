/**
 * Created by VMakarenko on 4/12/2015.
 */
angular.module("app").service('ThesisService',
    function ($http) {
        var url = "api/private/thesises/";

        this.getConfirmed = function (eventId, userId) {
            return $http.get(url + 'confirmed?userId='+userId+"&eventId="+eventId);
        };

        this.getWaitingForYourConfirmation = function (eventId, userId) {
            return $http.get(url + 'waitingForYourConfirmation', {params: {userId: userId, eventId: eventId}});

        };

        this.getWaitingForCoauthorConfirmation = function (eventId, userId) {
            return $http.get(url + 'waitingForCoauthorConfirmation', {params: {userId: userId, eventId: eventId}});
        };

        this.getByEvent = function (eventId, status) {
            return $http.get(url + 'getByEvent?eventId='+eventId + "&status=" + status);
        };

        this.save = function (thesis) {
            return $http.post(url, thesis);
        };

        this.delete = function (id) {
            return $http.delete(url + '?id=' + id);
        };


        this.find = function (id) {
            return $http.get(url + '?id=' + id);
        }

    }
)
;