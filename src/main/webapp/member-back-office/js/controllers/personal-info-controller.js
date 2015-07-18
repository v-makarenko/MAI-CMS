/**
 * Created by VMakarenko on 4/8/2015.
 */


angular.module('app').controller('PersonalInfoController', ['$scope', 'UserService',
    function ($scope, UserService) {
        $scope.getCurrentUser = function () {
            $scope.гser  = UserService.getCurrentUser();
        };

        $scope.save = function() {
            UserService.save($scope.currentUser).success(
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

