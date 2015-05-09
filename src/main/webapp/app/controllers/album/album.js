
angular.module('medekApp.controllers').controller('AlbumController',[
'$scope',
'$rootScope',
'$stateParams',
'$modal',
'$location',
'AlbumService',
'SupportService',
'GenreService',
function($scope, $rootScope, $stateParams, $modal, $location, AlbumService, SupportService,
		GenreService) {
    this.userLogin = $rootScope.user.login;

    if (typeof ($stateParams.albumId) != 'undefined') {
    	$scope.album = [ AlbumService.album($stateParams.albumId)
                               .then(
		                          function(response) {
		                        	  $('.mainlist').addClass('col-md-4');
		                        	  $('.mainlist').removeClass('col-md-8');
		                              console.log("Album : "+response.data);
		                              $scope.album = response.data;
		                              $('.itempanel').addClass('col-md-6');
		                          }, function(reason) {
		                              alert('FAILED !!!');
		                          }) ];
    } else {
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
  	    $('.mainlist').addClass('col-md-4');
	    $('.mainlist').removeClass('col-md-8');
        $('.itempanel').addClass('col-md-6');
    }

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
    
    $scope.editAlbum = function(id) {
    	$location.path('/albums/edit/'+id);
        $location.replace();
    }

    // for date picker
    $scope.open = function($event) {
      $event.preventDefault();
      $event.stopPropagation();

      $scope.opened = true;
    };
    
    $scope.saveAlbum = function() {
    	AlbumService.saveAlbum($scope.album);
    };
    
    $scope.addTrack = function() {
        var modalInstance = $modal.open({
            templateUrl: 'app/views/album/addedittrack.html',
            controller: 'TrackController',
            resolve: {
                tracks: function () {
                    return $scope.album.tracks;
                },
                track: function () {
                    return { id: null,
                            title: '',
                            albumId: $scope.album.id,
                            length: '',
                            trackNb: null,
                            artistId: null,
                            artist: ''
                    };
                }
              }
          });
    };

    $scope.editTrack = function(track) {
        var modalInstance = $modal.open({
            templateUrl: 'app/views/album/addedittrack.html',
            controller: 'TrackController',
            resolve: {
                tracks: function () {
                    return null;
                },
                track: function () {
                    return track;
                }
              }
          });
    };

    $scope.removeTrack = function(track) {
        for (var i=0; i<$scope.album.tracks.length; i++) {
            if (track.title === $scope.album.tracks[i].title) {
                if (track.id == null) {
                    $scope.album.tracks.splice(i, 1);
                } else {
                    $scope.album.tracks[i].title = 'deleted';
                }
                return;
            }
        }
    };

} ]);

angular.module('medekApp.controllers').controller('TrackController',[
'$scope',
'$modalInstance',
'TrackService',
'track',
'tracks',
function ($scope, $modalInstance, TrackService, track, tracks) {
    $scope.track = track;

    $scope.ok = function () {
    	TrackService.saveTrack($scope.track).then (
                function(response) {
                    alert('Save successful');
                    $modalInstance.close('success');
                }, function(reason) {
                    alert('Save failed');
                    $modalInstance.close('fail');
                });
    	if (tracks != null) {
    		tracks.push(track);
    	};
        $modalInstance.close('success');
    };

    $scope.cancel = function () {
      $modalInstance.dismiss('cancel');
    };
  }]);