
angular.module('medekApp.controllers').controller('AlbumsController',[
'$scope',
'$rootScope',
'$stateParams',
'$location',
'AlbumService',
function($scope, $rootScope, $stateParams, $location, AlbumService) {
    this.userLogin = $rootScope.user.login;
    
    $scope.getAlbums = function () {
    	if ($location.path() === '/albums/me') {
        	AlbumService.userAlbums($rootScope.user.id)
            .then(
               function(response) {
                   console.log("Albums : "+response.data);
                   $scope.albums = response.data;
               }, function(reason) {
                   alert('FAILED !!!');
               });
    	} else {
        	AlbumService.albums()
            .then(
               function(response) {
                   console.log("Albums : "+response.data);
                   $scope.albums = response.data;
               }, function(reason) {
                   alert('FAILED !!!');
               });
    	}
    };
    
    $scope.albums = [ $scope.getAlbums() ];
    
    $scope.openAlbum = function(id) {
    	$location.path($location.path()+'/'+id+'/view');
        $location.replace();
    };

} ]);