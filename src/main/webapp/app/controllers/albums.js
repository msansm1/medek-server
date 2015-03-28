
angular.module('medekApp.controllers').controller('AlbumsController',[
'$scope',
'$rootScope',
'$stateParams',
'$location',
'AlbumService',
function($scope, $rootScope, $stateParams, $location, AlbumService) {
    this.userLogin = $rootScope.user.login;
    
    $scope.albums = [ AlbumService.albums()
                               .then(
		                          function(response) {
		                              console.log("Albums : "+response.data);
		                              $scope.albums = response.data;
		                          }, function(reason) {
		                              alert('FAILED !!!');
		                          }) ];
    
    $scope.createAlbum = function() {
//        $location.path('/projects/'+$stateParams.projectId+'/0/form');
//        $location.replace();
    };

} ]);