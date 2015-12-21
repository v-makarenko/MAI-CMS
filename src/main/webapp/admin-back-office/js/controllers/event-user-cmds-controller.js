/**
 * Created by VMakarenko on 4/8/2015.
 */


angular.module('app').controller('EventUserCmdsController', ['$scope', '$rootScope', '$routeParams', '$modal', 'FinancialDocumentService',
    function ($scope, $rootScope, $routeParams, $modal, FinancialDocumentService) {

        $scope.userId = $routeParams.userId;
        $scope.eventId = $routeParams.id;

        $scope.requestDocs = function () {
            FinancialDocumentService.createDocsForUser($scope.userId, $scope.eventId)
                .success(function (data) {
                    if (data.status === 'OK') {
                        alert('Документы запрошены');
                    } else {
                        alert('Ошибка');
                    }
                })
        };

        FinancialDocumentService.getForUser($scope.userId, $scope.eventId).success(function(data){
            if(data.status == 'OK'){
                $scope.finDocList = data.data;
            }
        })
    }

]);

