angular.module("app").service('OrdersService',
    function OrdersService($http, $q) {
        var url = '/api/private/orders/';

        this.getOrders = function () {
            return $http.post(url + 'getAll', {})
        };
        this.confirmOrder = function () {
            var deferred = $q.defer();
            deferred.resolve({
                result: 'OK',
                msg: undefined
            });
        };
        this.closeOrder = function(id){
            var deferred = $q.defer();
            deferred.resolve({
                result: 'OK',
                msg: undefined
            });
        }


    });