/**
 * Created by VMakarenko on 4/8/2015.
 */


angular.module('app').controller('MainController',
    function ($scope, $rootScope, $location, AuthService) {
        $scope.rootScope = $rootScope;
        $scope.location = $location;


        // reaction for global events
        $rootScope.$on('events.exit', function () {
            AuthService.logout().success(function () {
                    $rootScope.isAuthenticated = false;
                    $location.path('/login');
                }
            )
        });

        $rootScope.$on('events.sendReport', function(){
            $('#send-report-modal').modal('show');
        });


        //$rootScope.isAuthenticated = true;

    }
);

