/**
 * Created by VMakarenko on 4/8/2015.
 */


angular.module('app').controller('WPEditModalController',
    function ($scope, $modalInstance, WPService, id) {
        $scope.wplace = {};

        WPService.find(id).success(function(data){
            if(data.status == 'OK'){
                $scope.wplace = data.data;
            }
        });

        $scope.save = function(){
            WPService.save($scope.wplace).success(function(data){
                if(data.status == 'OK'){
                    alert('Сохранено!');
                    $modalInstance.close('ok');
                }else{
                    alert('Ошибка!' + data.msg);
                }

            });
        };



        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        }
    });

