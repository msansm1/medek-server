
angular.module('medekApp.controllers').controller('AlbumsController',[
'$scope',
'$rootScope',
'$stateParams',
'$location',
'$modal',
'AlbumService',
function($scope, $rootScope, $stateParams, $location, $modal, AlbumService) {
    this.userLogin = $rootScope.user.login;
    $scope.filter = "";
    $scope.listLarge = true;
    
    $scope.getAlbums = function () {
        $scope.listLarge = true;
    	if ($location.path() === '/albums/me') {
        	AlbumService.userAlbumlist(0, 50, 'a.id', 'asc', $rootScope.user.id).then(
               function(response) {
                   console.log("Albums : "+response.data);
                   $scope.albums = response.data;
               }, function(reason) {
                   alert('FAILED !!!');
               });
    	} else {
        	AlbumService.albumlist(0, 50, 'id', 'asc')
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
        $scope.listLarge = false;
    	$location.path($location.path().substring(0,index)+'/view/'+id);
        $location.replace();
    };
    
    $scope.createAlbum = function() {
        var modalInstance = $modal.open({
            templateUrl: 'app/views/album/addalbum.html',
            controller: 'AddAlbumController',
            scope: $scope
          });
    };
    
    $scope.searchAlbums = function() {
        console.log('search criteria : '+$scope.filter);
    };

} ]);

angular.module('medekApp.controllers').controller('AddAlbumController',[
'$scope',
'$rootScope',
'$modalInstance',
'AlbumService',
'SupportService',
'GenreService',
function ($scope, $rootScope, $modalInstance, AlbumService, SupportService, GenreService) {
	$scope.album = { id: null,
			title: '',
			cover: '',
			releaseDate: null,
			genre: '',
			genreId: null,
			nbTracks: 0,
			support: '',
			supportId: null,
			artist: '',
			artistId: null,
			tracks: []
	};
    
    $scope.supports = [ SupportService.supports()
                        .then(
	                          function(response) {
	                              console.log("Supports : "+response.data);
	                              $scope.supports = response.data;
	                          }, function(reason) {
	                              alert('FAILED !!!');
	                          }) ];

    $scope.genres = [ GenreService.genres()
                        .then(
	                          function(response) {
	                              console.log("Genres : "+response.data);
	                              $scope.genres = response.data;
	                          }, function(reason) {
	                              alert('FAILED !!!');
	                          }) ];

    $scope.artists = [ AlbumService.artists()
                        .then(
	                          function(response) {
	                              console.log("Artists : "+response.data);
	                              $scope.artists = response.data;
	                          }, function(reason) {
	                              alert('FAILED !!!');
	                          }) ];

    // for date picker
    $scope.open = function($event) {
      $event.preventDefault();
      $event.stopPropagation();
      $scope.opened = true;
    };
	
	$scope.saveAlbum = function() {
    	AlbumService.saveAlbum($scope.album).then(
                function(response) {
                    $rootScope.alerts.push({type: 'success', msg: 'Album saved !'});
                	$scope.getAlbums();
                }, function(reason) {
                    $rootScope.alerts.push({type: 'danger', msg: 'Save album failed'});
                });
    };
	
    $scope.ok = function () {
    	$scope.saveAlbum();
        $modalInstance.close('success');
    };

    $scope.cancel = function () {
      $modalInstance.dismiss('cancel');
    };
  }]);