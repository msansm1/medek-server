
angular.module('medekApp', ['ui.router', 'medekApp.controllers', 
                          'ui.bootstrap', 'ui.grid', 'ui.grid.selection', 'pascalprecht.translate'])
         .config(function($translateProvider) {
            $translateProvider.useStaticFilesLoader({
                prefix: 'app/languages/',
                suffix: '.json'
            });
            $translateProvider.preferredLanguage('en');
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
            .state('books', {
                url: '/books',
                templateUrl : 'app/views/book/books.html',
                controller : 'BooksController'
            })
            .state('albums', {
                url: '/albums',
                templateUrl : 'app/views/album/albums.html',
                controller : 'AlbumsController'
            })
            .state('movies', {
                url: '/movies',
                templateUrl : 'app/views/movie/movies.html',
                controller : 'MoviesController'
            })
            .state('series', {
                url: '/series',
                templateUrl : 'app/views/series/series.html',
                controller : 'SeriesController'
            })
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
                return $state.includes(stateName);
            };
        });

var ctrls = angular.module('medekApp.controllers', ['medekApp.services']);

var svcs = angular.module('medekApp.services', []);