/**
 * Created by VMakarenko on 18.11.2015.
 */
angular.module("app").directive('thesisesList', ['$rootScope', function ($rootScope) {
    return {
        templateUrl: 'member-back-office/html/fragments/directives/thesises-list.html',
        link: function (scope, element, attrs) {
            scope.list
        },
        scope: {
            list: '=list',
            mode: '=mode'
        }
    }
}]);
