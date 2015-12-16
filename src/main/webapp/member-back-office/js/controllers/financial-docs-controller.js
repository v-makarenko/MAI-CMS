/**
 * Created by VMakarenko on 4/8/2015.
 */


angular.module('app').controller('FinancialDocsController', ['$scope', '$rootScope', '$routeParams', '$modal', 'FinancialService', 'ThesisService',
    function ($scope, $rootScope, $routeParams, $modal, FinancialService, ThesisService) {
        FinancialService.getDocs().success(function(data){
            if(data.status === 'OK'){
                $scope.finDocList = data.data;
            }
        });

    }
]);

