/**
 * Created by VMakarenko on 4/8/2015.
 */


angular.module('app').controller('LoginModalController', ['$scope', '$location', 'AuthService',
    function ($scope, $location, AuthService) {
        $scope.user = {};
        $scope.login = function () {
            AuthService.login(user).success(function(data){
                if(data.status == 'OK'){
                    if(data.data.type == 'MEMBER') {
                        $location.path('/member-back-office.html');
                    }
                    if(data.data.type == 'ADMIN' || data.data.type == 'SECRETARY') {
                        $location.path('/admin-back-office.html');
                    }
                    $scope.errorMsg = null;
                }else{
                    $scope.errorMsg = data.msg;
                    $scope.successMsg = null;
                }
            })
        };
        $scope.restorePassword = function () {
            AuthService.restorePassword().success(function(data){
                if(data.status == 'OK') {
                    $scope.successMsg = data.data.msg;
                    $scope.errorMsg = null;
                }else{
                    $scope.errorMsg = data.errMsg;
                    $scope.successMsg = null;
                }
            });
        }


    }
]);

