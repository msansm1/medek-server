
angular.module('medekApp', ['ui.router', 'medekApp.controllers', 
                          'ui.bootstrap', 'pascalprecht.translate', 'angularFileUpload'])
         .config(function($translateProvider) {
            $translateProvider.useStaticFilesLoader({
                prefix: 'app/languages/',
                suffix: '.json'
            });
            $translateProvider.preferredLanguage('fr');
        })
        .config(function($stateProvider, $urlRouterProvider, $httpProvider, $locationProvider) {
            $locationProvider.html5Mode(true);
            // default route
            $urlRouterProvider.otherwise("/");
            // states definitions
            $stateProvider
            .state('login', {
              url: '/',
              templateUrl : 'app/views/login.html',
              controller : 'AuthController'
            })
            .state('home', {
              url: '/home',
              templateUrl : 'app/views/home.html',
              controller : 'HomeController'
            })
            // BOOKS
            .state('books', {
            	abstract: true,
                url: '/books',
                templateUrl : 'app/views/book/books.html',
            })
            .state('books.all', {
                url: '/all',
                templateUrl : 'app/views/book/bookslist.html',
                controller : 'BooksController'
            })
            .state('books.mine', {
                url: '/me',
                templateUrl : 'app/views/book/bookslist.html',
                controller : 'BooksController'
            })
            .state('books.all.view', {
                url: '/view/:bookId',
                templateUrl : 'app/views/book/book.html',
                controller : 'BookController'
            })
            .state('books.mine.view', {
                url: '/view/:bookId',
                templateUrl : 'app/views/book/book.html',
                controller : 'BookController'
            })
            .state('books.mine.edit', {
                url: '/edit/:bookId',
                templateUrl : 'app/views/book/editbook.html',
                controller : 'BookController'
            })
            // ALBUMS
            .state('albums', {
            	abstract: true,
                url: '/albums',
                templateUrl : 'app/views/album/albums.html',
            })
            .state('albums.all', {
                url: '/all',
                templateUrl : 'app/views/album/albumslist.html',
                controller : 'AlbumsController'
            })
            .state('albums.mine', {
                url: '/me',
                templateUrl : 'app/views/album/albumslist.html',
                controller : 'AlbumsController'
            })
            .state('albums.all.view', {
                url: '/view/:albumId',
                templateUrl : 'app/views/album/album.html',
                controller : 'AlbumController'
            })
            .state('albums.mine.view', {
                url: '/view/:albumId',
                templateUrl : 'app/views/album/album.html',
                controller : 'AlbumController'
            })
            .state('albums.mine.edit', {
                url: '/edit/:albumId',
                templateUrl : 'app/views/album/editalbum.html',
                controller : 'AlbumController'
            })
            // MOVIES
            .state('movies', {
            	abstract: true,
                url: '/movies',
                templateUrl : 'app/views/movie/movies.html',
            })
            .state('movies.all', {
                url: '/all',
                templateUrl : 'app/views/movie/movieslist.html',
                controller : 'MoviesController'
            })
            .state('movies.mine', {
                url: '/me',
                templateUrl : 'app/views/movie/movieslist.html',
                controller : 'MoviesController'
            })
            .state('movies.all.view', {
                url: '/view/:movieId',
                templateUrl : 'app/views/movie/movie.html',
                controller : 'MovieController'
            })
            .state('movies.mine.view', {
                url: '/view/:movieId',
                templateUrl : 'app/views/movie/movie.html',
                controller : 'MovieController'
            })
            .state('movies.mine.edit', {
                url: '/edit/:movieId',
                templateUrl : 'app/views/movie/editmovie.html',
                controller : 'MovieController'
            })
            // SERIES
            .state('series', {
            	abstract: true,
                url: '/series',
                templateUrl : 'app/views/series/series.html',
            })
            .state('series.all', {
                url: '/all',
                templateUrl : 'app/views/series/serieslist.html',
                controller : 'SeriesController'
            })
            .state('series.mine', {
                url: '/me',
                templateUrl : 'app/views/series/serieslist.html',
                controller : 'SeriesController'
            })
            .state('series.all.view', {
                url: '/view/:serieId',
                templateUrl : 'app/views/series/serie.html',
                controller : 'SerieController'
            })
            .state('series.mine.view', {
                url: '/view/:serieId',
                templateUrl : 'app/views/series/serie.html',
                controller : 'SerieController'
            })
            .state('series.mine.edit', {
                url: '/edit/:serieId',
                templateUrl : 'app/views/series/editserie.html',
                controller : 'SerieController'
            })
            // ARTISTS
            .state('artists', {
                url: '/artists',
                templateUrl : 'app/views/artists/artists.html',
                controller : 'ArtistsController'
            })
            .state('artists.add', {
                url: '/add',
                templateUrl : 'app/views/artists/addartist.html',
                controller : 'ArtistController'
            })
            .state('artists.detail', {
                url: '/:artistId',
                templateUrl : 'app/views/artists/artist.html',
                controller : 'ArtistController'
            })
            .state('artists.edit', {
                url: '/edit/:artistId',
                templateUrl : 'app/views/artists/editartist.html',
                controller : 'ArtistController'
            })
            // ADMIN
            .state('admin', {
                url: '/admin',
                templateUrl : 'app/views/admin/home.html',
                controller : 'AdminHomeController'
            })
            .state('admin.users', {
                url: '/users',
                templateUrl : 'app/views/admin/user/users.html',
                controller : 'UsersController'
            })
            .state('admin.users.detail', {
                url: '/:userId',
                templateUrl : 'app/views/admin/user/user.html',
                controller : 'UserController'
            })
            .state('admin.lists', {
                url: '/lists',
                templateUrl : 'app/views/admin/list/lists.html',
                controller : 'ListsController'
            })
            .state('admin.config', {
                url: '/config',
                templateUrl : 'app/views/admin/config/config.html',
                controller : 'ConfigController'
            })
            // OTHERS
            .state('error', {
                url: 'error',
                resolve: {
                    errorObj: [function () {
                        return this.self.error;
                    }]
                },
                controller: 'ErrorCtrl',
                templateUrl: 'error.html' // displays an error message
            });
            /* CORS... */
            /* http://stackoverflow.com/questions/17289195/angularjs-post-data-to-external-rest-api */
            $httpProvider.defaults.useXDomain = true;
            delete $httpProvider.defaults.headers.common['X-Requested-With'];
        })
        .run(function ($rootScope) {
            $rootScope.isConnected = false; //global variable
            
            $rootScope.$on('$stateChangeStart', function(event, toState, toParams, fromState, fromParams){ 
                console.log("start change state : "+fromState.url+" => "+toState.url);
            });
            
            $rootScope.$on('$stateChangeSuccess', function(event, toState, toParams, fromState, fromParams){ 
                console.log("change state success : "+fromState.url+" => "+toState.url);
            });
            
            $rootScope.$on('$stateNotFound', function(event, unfoundState, fromState, fromParams){ 
                console.log('$stateNotFound : '+unfoundState.to); // "lazy.state"
                console.log('$stateNotFound : '+unfoundState.toParams); // {a:1, b:2}
                console.log('$stateNotFound : '+unfoundState.options); // {inherit:false} + default options
            });
            
            $rootScope.$on('$stateChangeError', function (event, toState, toParams, fromState, fromParams, error) {
                //event.preventDefault();
                //$state.get('error').error = { code: 123, description: 'Exception stack trace' }
                console.log("$stateChangeError : event => "+event);
                console.log("error stack : "+error);
                //return $state.go('error');
              });
            

            $rootScope.isActive = function(stateName) {
                //return $state.includes(stateName);
                return false;
            };
        });

var ctrls = angular.module('medekApp.controllers', ['medekApp.services']);

var svcs = angular.module('medekApp.services', []);

//compareTo directive
var compareTo = function() {
    return {
      require: "ngModel",
      scope: {
        otherModelValue: "=compareTo"
      },
      link: function(scope, element, attributes, ngModel) {

        ngModel.$validators.compareTo = function(modelValue) {
          return modelValue == scope.otherModelValue;
        };

        scope.$watch("otherModelValue", function() {
          ngModel.$validate();
        });
      }
    };
  };

app.directive("compareTo", compareTo);

// ngReally directive
/**
* A generic confirmation for risky actions.
* Usage: Add attributes: ng-really-message="Are you sure"? ng-really-click="takeAction()" function
*/
app.directive('ngReallyClick', [function() {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            element.bind('click', function() {
                var message = attrs.ngReallyMessage;
                if (message && confirm(message)) {
                    scope.$apply(attrs.ngReallyClick);
                }
            });
        }
    }
}]); 