/**
 * Created by VMakarenko on 18.11.2015.
 */
angular.module("app").directive('thesisesList', ['$rootScope', 'ThesisService',
    function ($rootScope, ThesisService) {
    return {
        templateUrl: 'member-back-office/html/fragments/directives/thesises-list.html',
        link: function (scope, element, attrs) {
            scope.deleteItem = function(id){
                ThesisService.delete(id);
            }
        },
        scope: {
            list: '=list',
            mode: '=mode'
        }
    }
}]);
