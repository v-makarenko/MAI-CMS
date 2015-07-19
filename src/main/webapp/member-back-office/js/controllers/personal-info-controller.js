/**
 * Created by VMakarenko on 4/8/2015.
 */


angular.module('app').controller('PersonalInfoController', ['$scope', 'UserService',
    function ($scope, UserService) {
        $scope.getCurrentUser = function () {
            UserService.getCurrentUser().success(function(data){
                if(data.status = 'OK'){
                    $scope.user = data.data;
                }
            });
        };

        $scope.save = function() {
            UserService.save($scope.user).success(
                function(data){
                    if(data.status == 'OK'){
                        alert('OK');
                    }else{
                        alert('Ошибка: ' + data.errorMsg);
                    }
                }
            );
        };


        $scope.getCurrentUser();

    }
]);

