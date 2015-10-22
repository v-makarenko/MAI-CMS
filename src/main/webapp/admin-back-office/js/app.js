/**
 * Created by VMakarenko on 2/7/15.
 */

angular.module('app', [ 'ui.bootstrap', 'datePicker',
    'ngRoute']).config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider.
            when('/users', {
                templateUrl: 'admin-back-office/html/fragments/users.html',
                controller: 'UsersController'
            }).
            when('/events', {
                templateUrl: 'admin-back-office/html/fragments/events.html',
                controller: 'EventsController'
            }).
            when('/workingPlaces', {
                templateUrl: 'admin-back-office/html/fragments/wplaces.html',
                controller: 'WPController'
            }).

            when('/events/:id', {
                templateUrl: 'admin-back-office/html/fragments/events-edit.html',
                controller: 'EventEditController'
            }).

            otherwise({
                redirectTo: '/users'
            });
    }])
    .run(function ($rootScope, $location, AuthService) {
    AuthService.isAuthenticated().success(function (result) {
        $rootScope.authenticated = result.status == 'OK';

        var callback = function() {
            if (!$rootScope.authenticated) {
                window.location.href = '../index.html';
            }
        };

        $rootScope.$on('$routeChangeStart', function (event, next, current) {
            callback();
        });

        callback();

    });
});