/**
 * Created by VMakarenko on 4/12/2015.
 */
angular.module("app").service('FinancialDocumentTypeService',
    function ($http) {
        var url = "api/private/financialDocumentTypes/";

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

    }
)
;