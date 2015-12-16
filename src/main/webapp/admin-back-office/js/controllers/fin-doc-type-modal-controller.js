/**
 * Created by VMakarenko on 4/8/2015.
 */


angular.module('app').controller('FinDocTypeModalController',
    function ($scope, $modalInstance, FinancialDocumentTypeService, data, FileUploader) {
        $scope.currentDocType = {
            eventId: data.eventId
        };

        if(data.id) {
            FinancialDocumentTypeService.find(data.id).success(function (data) {
                if (data.status == 'OK') {
                    $scope.currentDocType = data.data;
                }
            });
        }

        $scope.save = function(){
            FinancialDocumentTypeService.save($scope.currentDocType).success(function(data){
                if(data.status == 'OK'){
                    alert('Сохранено!');
                    $modalInstance.close('ok');
                }else{
                    alert('Ошибка!' + data.msg);
                }
            });
        };

        $scope.uploader = new FileUploader({
            url: 'api/private/files',
            autoUpload: true,
            removeAfterUpload : true,
            onSuccessItem : function(item, data){
                $scope.currentDocType.exampleFileId = data.data;
            },
            onBeforeUploadItem : function(item){
                item.url +="/" + item._file.name;
            }
        });



        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        }
    });

