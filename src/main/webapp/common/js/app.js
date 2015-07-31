/**
 * Created by VMakarenko on 2/7/15.
 */

angular.module('app', [
    'ngRoute']).config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider.
            when('/orders', {
                templateUrl: 'html/orders.html',
                controller: 'OrdersController'
            }).
            when('/menuEdit/:edit', {
                templateUrl: 'html/menu-edit.html',
                controller: 'MenuEditController'
            }).
            when('/stats', {
                templateUrl: 'html/stats.html',
                controller: 'StatsController'
            }).
            when('/login', {
                templateUrl: 'html/login.html',
                controller: 'LoginController'
            }).
            when('/signUp', {
                templateUrl: 'html/signUp.html',
                controller: 'SignUpController'
            }).
            when('/settings', {
                templateUrl: 'html/settings.html',
                controller: 'SettingsController'
            }).

            otherwise({
                redirectTo: '/login'
            });
    }]).run(function ($rootScope, AuthService, $location) {
    AuthService.isAuthenticated().success(function (data) {
        $rootScope.isAuthenticated = data;
        AuthService.getCurrentUser().success(function(data){
            $rootScope.currentUser = data.data;
            $rootScope.$broadcast('event.userLoaded');
        });
        callback();
    });


    var callback = function () {
        if($rootScope.isAuthenticated === undefined) return;
        if ($location.url() != '/login'
            && $location.url() != '/signUp'
            && !$rootScope.isAuthenticated) {
            $location.path('/login');
        }
    };

    $rootScope.$on('$routeChangeStart', function (event, next, current) {
        callback();
    });


});