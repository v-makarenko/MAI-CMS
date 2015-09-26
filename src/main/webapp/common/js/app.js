/**
 * Created by VMakarenko on 2/7/15.
 */

angular.module('app', [
    'ngRoute']).config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider.
            when('/users', {
                templateUrl: '/admin-back-office/html/users.html',
                controller: 'UsersController'
            }).
            when('/events', {
                templateUrl: '/admin-back-office/html/fragments/events.html',
                controller: 'EventsController'
            }).
            when('/messages', {
                templateUrl: '/common/html/fragments/messages.html',
                controller: 'MessagesController'
            }).

            otherwise({
                redirectTo: '/events'
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