/**
 * Created by VMakarenko on 4/8/2015.
 */


angular.module('app').controller('AddCoauthorModalController', ['$scope', '$routeParams', '$modal','$modalInstance','ThesisService', 'SectionsService', 'UsersService',
    function ($scope, $routeParams, $modal, $modalInstance, ThesisService, SectionsService, UsersService) {
        $scope.newCoauthor = {dtype:'reg'};
        $scope.users = [];
        UsersService.getAllConfRegistered($routeParams.id).success(function(data){
            if(data.status==='OK'){
                $scope.users = data.data;
            }
        });

        $scope.ok = function(){
            if($scope.newCoauthor.dtype == 'reg'){
                var user =  _.find($scope.users, {id: $scope.newCoauthor.userId});
                $scope.newCoauthor.snpShort = user.surname + ' ' + user.name;
            }
            $modalInstance.close($scope.newCoauthor);
        };
        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };


    }
]);

