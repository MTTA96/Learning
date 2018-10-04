import React from 'react'
import {
    View,
    Text,
    TextInput,
    StyleSheet,
    EStyleSheet,
    Image,
    ImageBackground,
    Button,
    Alert,
    Keyboard,
    TouchableOpacity,
} from 'react-native'
import axios from 'axios'
import axiosMiddleware from 'redux-axios-middleware'
import { red, yellow } from 'ansi-colors'
import Colors from './../constants/Colors'

import reducer from './../reducer/reducer'

const clinet = axios.create({
    baseURL: 'https://www.themoviedb.org',
    responseType: 'json'
})

const store = createStore(reducer, applyMiddleware(axiosMiddleware(client)))

export default class Signin extends React.Component {

    constructor(props) {
        super(props)
        // this._onPressLogin = this._onPressLogin.bind(this)
        this.state = {
            title: "MOVIEDB"
        }
    }

    // Actions

    _onPressLogin = () => {

        this.txtUserName.blur()
        this.txtPassword.blur()
    }

    render() {
        return (
            <ImageBackground
                source={require('./../assets/images/login-background.png')}
                style={styles.backgroundImage}>

                <TouchableOpacity style={styles.container} activeOpacity = {1.0} onPress = {Keyboard.dismiss}>

                    <View style={styles.infoContainer}>

                        {/* Title */}

                        <Text style={styles.titleText}> MOVIEDB </Text>
                        <View style={{ flex: 1, height: 55 }} />

                        {/* Input fields */}

                        <View>

                            {/* Username */}
                            <TextInput
                                style={styles.textInput}
                                autoCapitalize = 'none'
                                placeholder="Username"
                                placeholderTextColor={Colors.hintColor} 
                                ref={component => this.txtUserName = component}
                                blurOnSubmit = {false}
                                onSubmitEditing = {() => {this.txtPassword.focus()}}/>

                            <View style={styles.separatorView} />

                            {/* Password */}
                            <TextInput style={styles.textInput}
                                autoCapitalize = 'none'
                                placeholder="Password"
                                placeholderTextColor={Colors.hintColor} 
                                ref = {component => this.txtPassword = component}/>

                        </View>
                        <View style={{ flex: 1, height: 30 }} />

                        {/* Button login */}

                        <View style={styles.buttonLoginContainer}>
                            <Button
                                onPress={this._onPressLogin}
                                title="Login"
                                color="white"
                                style={styles.buttonLogin} />
                        </View>

                        {/* Sign up */}

                        <Text style={styles.signUpText}>
                            <Text style={{ fontWeight: 'bold' }}>OR{"\n"}</Text>

                            <View style={{ height: 30 }} />
                            <Text style={{ fontWeight: '100' }}>{"\n"}Sign up via website themoviedb.org</Text>
                        </Text>
                    </View>


                    {/* Logos */}

                    <View style={styles.logoContainer}>

                        <Image source={require('./../assets/images/logo.png')} />

                        <Image source={require('./../assets/images/pbe-logo.png')} />
                    </View>

                </TouchableOpacity>

            </ImageBackground>
        )
    }

}
// const rem = EStyleSheet.build({$rem: entireScreenWidth / 380});
const styles = StyleSheet.create({

    container: {
        flex: 1,
        justifyContent: 'center',
    },
    
    backgroundImage: {
        width: '100%',
        height: '100%',
    },

    infoContainer: {
        // flex: 1,
        position: 'absolute',
        // top: 155,
        left: 35,
        right: 37,
        // backgroundColor: 'red'
    },

    titleText: {
        color: 'white',
        fontFamily: 'Cochin',
        fontWeight: 'bold',
        fontSize: 40,
        textAlign: 'center',
        // backgroundColor: 'yellow'
        // fontWeight: 'bold'
    },

    separatorView: {
        height: 1,
        backgroundColor: Colors.tintColor
    },

    textInput: {
        left: 0,
        right: 0,
        height: 50,
        paddingLeft: 11,
        backgroundColor: 'white',
        fontFamily: 'Cochin',
        fontSize: 14
    },

    buttonLoginContainer: {
        flex: 1,
        left: 0,
        right: 0,
        height: 50,
        justifyContent: 'center',
        backgroundColor: Colors.tintColor
    },

    buttonLogin: {
        fontSize: 17
    },

    signUpText: {
        width: '60%',
        color: 'white',
        textAlign: 'center',
        alignSelf: 'center',
        fontSize: 17,
        top: 15,
    },

    logoContainer: {
        // backgroundColor: 'white',
        flexDirection: 'row',
        left: 36,
        right: 37,
        bottom: 26,
        position: 'absolute',
        alignItems: 'center',
        justifyContent: 'space-between'

    }

})