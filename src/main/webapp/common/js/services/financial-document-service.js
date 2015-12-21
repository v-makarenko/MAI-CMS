/**
 * Created by VMakarenko on 4/12/2015.
 */
angular.module("app").service('FinancialDocumentService',
    function ($http) {
        var url = "api/private/financialDocuments/";

        this.save = function (docType) {
            return $http.put(url, docType)
        };

        this.find = function (id) {
            return $http.get(url + id);
        };
        this.delete = function (id) {
            return $http.delete(url + id);
        };

        this.getAll = function (eventId) {
            return $http.get(url + 'getAll', {params: {eventId: eventId}});
        };
        this.send = function (fromId, toId, messageText, attach) {
            return $http.post(url + 'send', {fromId: fromId, toId: toId, messageText: messageText, attach: attach});
        };

        this.getForEvent = function(eventId){
            return $http.get(url + 'forEvent/' + eventId);
        };

        this.getForUser= function(userId, eventId){
            return $http.get(url + 'forUser?eventId=' + eventId + '&userId=' + userId) ;
        };

        this.createDocsForUser = function(userId,eventId){
            return $http.post(url + 'createDocsForUser/', {userId: userId, eventId: eventId});
        }

    }
)
;