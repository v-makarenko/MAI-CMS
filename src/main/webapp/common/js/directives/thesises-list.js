/**
 * Created by VMakarenko on 18.11.2015.
 */
angular.module("app").directive('thesisesList', ['$rootScope', 'ThesisService',
    function ($rootScope, ThesisService) {
    return {
        templateUrl: 'common/html/fragments/directives/thesises-list.html',
        link: function (scope, element, attrs) {
            scope.deleteItem = function(id){
                ThesisService.delete(id).success(function(data){
                    $rootScope.$broadcast('event.thesisesList.refresh');
                });
            };

            scope.confirm = function(id){
                ThesisService.confirm(id).success(function(data){
                    $rootScope.$broadcast('event.thesisesList.refresh');
                });
            };

            scope.deleteFromCA = function(id){
                ThesisService.deleteFromCA(id).success(function(data){
                    $rootScope.$broadcast('event.thesisesList.refresh');
                });
            };

            scope.editItem = function(id){
                $rootScope.$broadcast('event.thesisesList.edit', id);
            }
        },
        scope: {
            list: '=list',
            mode:'@mode'
        }
    }
}]);
