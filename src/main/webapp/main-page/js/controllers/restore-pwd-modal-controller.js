/**
 * Created by VMakarenko on 4/8/2015.
 */


angular.module('app').controller('RestorePwdModalController', ['$scope', 'AuthService',
    function ($scope, AuthService) {
        $scope.notRegisteredShow = false;
        $scope.successShow = false;
        $scope.restorePassword = function () {
            AuthService.restorePassword($scope.email).success(function(data){
                if(data.status == 'OK') {
                    $scope.successShow = true;
                }else{
                    $scope.notRegisteredShow = true;
                    $scope.restorePwdForm.$setUntouched();
                }
            });
        }


    }
]);

