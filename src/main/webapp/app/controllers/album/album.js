
angular.module('medekApp.controllers').controller('AlbumController',[
'$scope',
'$rootScope',
'$stateParams',
'$modal',
'$location',
'$upload',
'AlbumService',
'SupportService',
'GenreService',
function($scope, $rootScope, $stateParams, $modal, $location, $upload, AlbumService, SupportService,
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

    $scope.artists = [ AlbumService.artists()
                               .then(
		                          function(response) {
		                              console.log("Artists : "+response.data);
		                              $scope.artists = response.data;
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
    	AlbumService.saveAlbum($scope.album).then(
                function(response) {
                    $rootScope.alerts.push({type: 'success', msg: 'Album saved !'});
                	$scope.$parent.getAlbums();
                }, function(reason) {
                    $rootScope.alerts.push({type: 'danger', msg: 'Save album failed'});
                });
    };
    
    $scope.addTrack = function() {
        var modalInstance = $modal.open({
            templateUrl: 'app/views/album/addedittrack.html',
            controller: 'TrackController',
            resolve: {
                tracks: function () {
                    return $scope.album.tracks;
                },
                artists: function () {
                    return $scope.artists;
                },
                track: function () {
                    return { id: null,
                            title: '',
                            albumId: $scope.album.id,
                            length: '',
                            trackNb: $scope.album.tracks.length+1,
                            artistId: $scope.album.artistId,
                            artist: $scope.album.artist
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
                artists: function () {
                    return $scope.artists;
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
    
    // cover upload
    $scope.onFileSelect = function($files) {
        for (var i = 0; i < $files.length; i++) {
          var file = $files[i];
          $scope.upload = $upload.upload({
            url: 'services/albums/'+$scope.album.id+'/coverupload',
            method: 'POST',
            headers: {'securtoken': $rootScope.user.token},
            //withCredentials: true,
            data: {id: $scope.album.id},
            file: file // or list of files ($files) for html5 only
          }).progress(function(evt) {
            console.log('percent: ' + parseInt(100.0 * evt.loaded / evt.total, 10));
          }).success(function(data, status, headers, config) {
            console.log('SUCCESS !! '+data);
          });
        }
    };
    
    $scope.addAlbumToMyCollec = function(album) {
        var modalInstance = $modal.open({
            templateUrl: 'app/views/album/addtocollec.html',
            controller: 'AlbumCollecController',
            resolve: {
                album: function () {
                    return album;
                }
              }
          });
    };

} ]);

angular.module('medekApp.controllers').controller('TrackController',[
'$scope',
'$modalInstance',
'TrackService',
'track',
'tracks',
'artists',
function ($scope, $modalInstance, TrackService, track, tracks, artists) {
	$scope.artists = artists;
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

angular.module('medekApp.controllers').controller('AlbumCollecController',[
'$scope',
'$rootScope',
'$modalInstance',
'AlbumService',
'album',
function ($scope, $rootScope, $modalInstance, AlbumService, album) {
	$scope.album = album;
	$scope.myalbum = {
			albumId: album.id,
			userId: $rootScope.user.id,
			rating: 0,
			comment: 0,
			signed: false
	};

    $scope.ok = function () {
    	AlbumService.addAlbumToCollection($scope.myalbum).then (
                function(response) {
                    alert('Add successful');
                    $modalInstance.close('success');
                }, function(reason) {
                    alert('Add failed');
                    $modalInstance.close('fail');
                });
        $modalInstance.close('success');
    };

    $scope.cancel = function () {
      $modalInstance.dismiss('cancel');
    };
  }]);