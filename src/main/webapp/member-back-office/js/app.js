/**
 * Created by VMakarenko on 7/17/2015.
 */
angular.module('app', [
    'ngRoute'
]).config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider.
            when('/home', {
                templateUrl: 'html/fragments/home.html',
                controller: 'HomeController'
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
