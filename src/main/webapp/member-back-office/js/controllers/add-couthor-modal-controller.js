/**
 * Created by VMakarenko on 4/8/2015.
 */


angular.module('app').controller('AddCoauthorModalController', ['$scope', '$routeParams', '$modal','$modalInstance','ThesisService', 'SectionsService', 'UsersService',
    function ($scope, $routeParams, $modal, $modalInstance, ThesisService, SectionsService, UsersService) {
        $scope.users = [{id: 1, surname: 'dsagds'}];
        UsersService.getAllConfRegistered($routeParams.id).success(function(data){
            if(data.status==='OK'){
                $scope.users = data.data;
            }
        });

        $scope.ok = function(){
            $modalInstance.close();
        };
        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };

        $scope.coauthorMode = 'registered';


    }
]);

