/**
 * Created by VMakarenko on 4/8/2015.
 */


angular.module('app').controller('UserEditModalController',
    function ($scope, $modalInstance, UsersService, id) {
        UsersService.find(id).success(function(data){
            if(data.status == 'OK'){
                $scope.user = data.data;
            }
        });

        $scope.save = function(){
            UsersService.save($scope.user).success(function(data){
                if(data.status == 'OK'){
                    alert('Сохранено!');
                    $modalInstance.close('ok');
                }else{
                    alert('Ошибка!' + data.msg);
                }

            });
        }



        $scope.cancel = function () {

            $modalInstance.dismiss('cancel');
        }
    });

