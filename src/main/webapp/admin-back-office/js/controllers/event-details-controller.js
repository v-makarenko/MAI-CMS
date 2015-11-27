/**
 * Created by VMakarenko on 4/8/2015.
 */


angular.module('app').controller('EventDetailsController', ['$scope', '$routeParams', 'ThesisService',
    function ($scope, $routeParams, ThesisService) {
        $scope.eventId = $routeParams.id;
        $scope.thesisesList = [];
        $scope.loadThesises = function () {
            ThesisService.getByEvent($scope.eventId, $scope.status).success(function(data){
                $scope.thesisesList = data.data;
            });
        };

        $scope.loadThesises();
    }
]);

