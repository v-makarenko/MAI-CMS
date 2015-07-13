/**
 * Created by VMakarenko on 4/9/2015.
 */

angular.module("app").directive('setActiveTab', [ '$timeout', '$rootScope', function($timeout, $rootScope) {
    var def = {
        restrict : 'A',
        terminal : true,
        transclude : true,
        link : function(scope, element, attrs) {
            $rootScope.$broadcast('main.tabSelected', scope.tabId);
        },
        scope: {
            tabId: '=tabId'
        }
    };
    return def;
}])
