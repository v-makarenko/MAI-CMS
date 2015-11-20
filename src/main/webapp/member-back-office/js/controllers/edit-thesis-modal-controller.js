/**
 * Created by VMakarenko on 4/8/2015.
 */


angular.module('app').controller('EditThesisModalController', ['$scope', '$rootScope', '$routeParams', '$modal','$modalInstance','ThesisService', 'SectionsService',
    function ($scope, $rootScope, $routeParams, $modal, $modalInstance, ThesisService, SectionsService) {
        $scope.eventId = $routeParams.id;

        $scope.currentThesis = {
            eventId: $scope.eventId
        };

        $scope.ok = function(){
            $scope.currentThesis.userId = $rootScope.currentUser.id;
            ThesisService.save($scope.currentThesis).success(function(data){

            });
            $modalInstance.close();
        };
        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };

        $scope.addCoauthor = function(){
            $scope.addCoauthorModal = $modal.open({
                templateUrl: 'member-back-office/html/fragments/modal/add-coauthor-modal.html',
                controller: 'AddCoauthorModalController',
                //size: size,
                resolve: {
                    data: function () {
                        return {}
                    }

                }
            });
        };

        SectionsService.getAll($routeParams.id).success(function(data){
            if(data.status === 'OK'){
                $scope.sections = data.data;
            }
        });

    }
]);

