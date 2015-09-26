/**
 * Created by VMakarenko on 7/17/2015.
 */
angular.module('app', [
    'ngRoute', 'ui.bootstrap'
]).config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider.
            when('/home', {
                templateUrl: '/member-back-office/html/fragments/home.html',
                controller: 'HomeController'
            }).
            when('/pinfo', {
                templateUrl: '/member-back-office/html/fragments/pinfo.html',
                controller: 'PersonalInfoController'
            }).
            when('/psettings', {
                templateUrl: '/member-back-office/html/fragments/psettings.html',
                controller: 'PersonalSettingsController'
            }).
            when('/events', {
                templateUrl: '/member-back-office/html/fragments/events.html',
                controller: 'EventsController'
            }).
            when('/messages', {
                templateUrl: '/member-back-office/html/fragments/messages.html',
                controller: 'MessagesController'
            }).
            when('/distribMail', {
                templateUrl: '/member-back-office/html/fragments/distrib-mail.html',
                controller: 'EmailDistrController'
            }).
            when('/emailTemplates', {
                templateUrl: '/member-back-office/html/fragments/email-templates.html',
                controller: 'EmailTemplatesController'
            }).
            otherwise({
                redirectTo: '/home'
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
