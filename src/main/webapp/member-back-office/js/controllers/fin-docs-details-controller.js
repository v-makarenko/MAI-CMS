/**
 * Created by VMakarenko on 4/8/2015.
 */


angular.module('app').controller('FinDocDetailsController', ['$scope', '$rootScope', '$routeParams', '$modal', 'FinancialDocumentService', 'ThesisService',
    function ($scope, $rootScope, $routeParams, $modal, FinancialDocumentService, ThesisService) {
        $scope.currentEventId = $routeParams.id;
        $scope.currentDocId = $routeParams.finDocId;


        $scope.load = function(){
            FinancialDocumentService.find($scope.currentDocId).success(function(data){
                if(data.status === 'OK'){
                    $scope.currentFinDoc = data.data;
                }
            });
        };


        $rootScope.$on('user.loaded', $scope.load);
    }
]);

