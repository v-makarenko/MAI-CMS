/**
 * Created by VMakarenko on 4/8/2015.
 */


angular.module('app').controller('LoginController',
    function ($scope, $rootScope, $routeParams, $location, AuthService) {

        $scope.isSuccess = $routeParams['successFlag'] == 'success';
        $scope.credentials = {};

        $scope.loginF = function () {
            AuthService.login($scope.credentials.login, $scope.credentials.password).success(function (response) {
                $rootScope.isAuthenticated = true;
                $location.path('/orders');
            }).error(function(){
                    $scope.error = 'Неверный логин или пароль';
                }
            );
        };

        $scope.$watch('credentials.login+credentials.password', function () {
            $scope.error = undefined;
        });

    }
)
;

