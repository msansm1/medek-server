
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
    	var index = 0;
    	if ($location.path().indexOf('/me/') > -1) {
     	   index = 10;
        } else {
     	   index = 11;
        }
    	$location.path($location.path().substring(0,index)+'/view/'+id);
        $location.replace();
    };

} ]);