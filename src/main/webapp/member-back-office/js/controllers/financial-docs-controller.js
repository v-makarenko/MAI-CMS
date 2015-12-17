/**
 * Created by VMakarenko on 4/8/2015.
 */


angular.module('app').controller('FinancialDocsController', ['$scope', '$rootScope', '$routeParams', '$modal', 'FinancialDocumentService', 'ThesisService',
    function ($scope, $rootScope, $routeParams, $modal, FinancialDocumentService, ThesisService) {
        FinancialDocumentService.getForEvent($routeParams.id).success(function(data){
            if(data.status === 'OK'){
                $scope.finDocList = data.data;
            }
        });

    }
]);

