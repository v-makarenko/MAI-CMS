/**
 * Created by VMakarenko on 4/8/2015.
 */


angular.module('app').controller('PersonalInfoController', ['$scope', 'UsersService',
    function ($scope, UsersService) {
        $scope.pass = {};
        $scope.getCurrentUser = function () {
            UsersService.getCurrentUser().success(function(data){
                if(data.status = 'OK'){
                    $scope.user = data.data;
                }
            });
        };

        $scope.save = function() {
            UsersService.save($scope.user).success(
                function(data){
                    if(data.status == 'OK'){
                        alert('OK');
                    }else{
                        alert('Ошибка: ' + data.errorMsg);
                    }
                }
            );
        };

        $scope.changePassword = function(){
            UsersService.changePassword($scope.pass.pass1, $scope.user.id).success(function(){
                alert('Успешно!');
            });
        };

        $scope.getCurrentUser();
    }
]);

