/**
 * Created by VMakarenko on 4/8/2015.
 */


angular.module('app').controller('LoginModalController', ['$scope', '$location', '$modal', 'AuthService',
    function ($scope, $location, $modal, AuthService) {
        $scope.user = {};
        $scope.login = function () {
            AuthService.login($scope.user).success(function(data){
                if(data.status == 'OK'){
                    if(data.data.userType == 'MEMBER') {
                        window.location.href = 'member-back-office.html';
                    }
                    if(data.data.userType == 'ADMIN' || data.data.userType == 'SECRETARY') {
                        window.location.href = 'admin-back-office.html';
                    }
                    $scope.errorMsg = null;
                }else{
                    $scope.errorMsg = 'Неверный логин или пароль!';
                    $scope.successMsg = null;
                }
            })
        };

        $scope.$watch('user.email+user.password', function(){
            $scope.errorMsg = null;
        });
        $scope.restorePassword = function(){
            $scope.loginModal = $modal.open({
                templateUrl: 'main-page/html/modals/restore-pwd-modal.html',
                controller: 'RestorePwdModalController'
            });
        }
    }
]);

