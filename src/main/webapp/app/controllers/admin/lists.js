
angular.module('medekApp.controllers').controller('ListsController',[
'$scope',
'$rootScope',
'$modal',
function($scope, $rootScope, $modal) {
    this.userLogin = $rootScope.user.login;
    $scope.lists = [];
    
    $scope.lists.push({name : "collections"});
    
    $scope.lists.push({name : "editors"});
    
    $scope.lists.push({name : "genres"});
    
    $scope.lists.push({name : "langages"});
    
    $scope.lists.push({name : "supports"});
    
    $scope.lists.push({name : "storygenres"});

} ]);
