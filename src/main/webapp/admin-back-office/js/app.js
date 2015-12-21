/**
 * Created by VMakarenko on 2/7/15.
 */

angular.module('app', ['ui.bootstrap','jkuri.datepicker','angularFileUpload',
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
            when('/events/:id/details', {
                templateUrl: 'admin-back-office/html/fragments/event-details.html',
                controller: 'EventDetailsController'
            }).
            when('/events/:id/user/:userId', {
                templateUrl: 'admin-back-office/html/fragments/event-user-cmds.html',
                controller: 'EventUserCmdsController'
            }).
            when('/logs', {
                templateUrl: 'admin-back-office/html/fragments/logs.html',
                controller: 'LogsController'
            }).
            when('/messages', {
                templateUrl: 'admin-back-office/html/fragments/messages.html',
                controller: 'MessagesController'
            }).
            otherwise({
                redirectTo: '/users'
            });
    }])
    .run( function($rootScope, $location, AuthService) {
        AuthService.isAuthenticated().success(function (result) {
            $rootScope.authenticated = result.status == 'OK';
            var callback = function () {
                if (!$rootScope.authenticated) {
                    window.location.href = 'index.html';
                } else {
                    if (!$rootScope.currentUser) {
                        AuthService.getCurrentUser().success(function (data) {
                            if (data.status == 'OK') {
                                $rootScope.currentUser = data.data;
                                $rootScope.$broadcast('user.loaded')
                            }

                        });
                    }
                }
            };

            $rootScope.$on('$routeChangeStart', function (event, next, current) {
                callback();
            });

            callback();

        });

    });