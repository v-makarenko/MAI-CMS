/**
 * Created by VMakarenko on 2/7/15.
 */

angular.module('app', [
    'ngRoute', 'ui.bootstrap']).config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider.
            when('/aboutUs', {
                templateUrl: 'main-page/html/fragments/about-us.html',
                controller: 'MainPageController'
            }).
            when('/aboutCurrentEvent', {
                templateUrl: 'main-page/html/fragments/about-current-event.html',
                controller: 'MainPageController'
            }).
            when('/archive', {
                templateUrl: 'main-page/html/fragments/archive-list.html',
                controller: 'ArchiveController'
            }).
            when('/signUp', {
                templateUrl: 'main-page/html/fragments/sign-up.html',
                controller: 'SignUpController'
            }).

            otherwise({
                redirectTo: 'aboutCurrentEvent'
            });
    }]);
