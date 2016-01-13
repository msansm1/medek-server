
angular.module('medekApp.controllers').controller('ListsController',[
'$scope',
'$rootScope',
'$modal',
'CollectionService',
function($scope, $rootScope, $modal, CollectionService) {
    this.userLogin = $rootScope.user.login;
    $scope.lists = [];
    
    $scope.lists.push({name : "booktypes"});
    
    var collections = {name : "collections", items : []};
    CollectionService.collections().then(
       function(response) {
           console.log("Collections : "+response.data);
           collections.items = response.data;
       }, function(reason) {
           alert('FAILED !!!');
       });
    $scope.lists.push(collections);
    
    $scope.lists.push({name : "editors"});
    
    $scope.lists.push({name : "genres"});
    
    $scope.lists.push({name : "langages"});
    
    $scope.lists.push({name : "supports"});
    
    $scope.lists.push({name : "storygenres"});

} ]);
