/**
 * Created by VMakarenko on 4/8/2015.
 */


angular.module('app').controller('SignUpController',
    function ($scope, $location, AuthService) {
        $scope.i = 1;
        $scope.newUser = {};
        $scope.signUp = function () {
            AuthService.signUp($scope.newUser)
                .success(function () {
                    $location.path('#/login')
                });
        }

    });

