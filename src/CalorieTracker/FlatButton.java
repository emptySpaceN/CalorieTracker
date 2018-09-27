/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CalorieTracker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

/**
 *
 * @author Pippin
 */
public class FlatButton extends JLabel
{

    private boolean mouseButtonDown = false;
    private boolean mouseEntered = false;

    private BufferedImage imageOriginalSize = null;
    private BufferedImage imageSmallSize = null;
    private BufferedImage imageLargeSize = null;

    public FlatButton()
    {
        setOpaque(true);
        setHorizontalAlignment(CENTER);
        setVerticalAlignment(CENTER);
        setBackground(new Color(225, 225, 225));
        setForeground(new Color(0, 0, 0));
        setBorder(new LineBorder(new Color(173, 173, 173), 1, false));
        setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));

        addMouseListener(new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {

            }

            @Override
            public void mousePressed(MouseEvent e)
            {
                mouseButtonDown = true;

                if (getBorder() != null)
                {
                    setBackground(new Color(204, 228, 247));
                    setBorder(new LineBorder(new Color(0, 120, 215), 1, true));
                } else
                {
                    if (getIcon() != null)
                    {
                        if (imageOriginalSize == null)
                        {
                            InitialiseIcon();

                            setIcon(new ImageIcon(imageSmallSize));

                        } else
                        {
                            setIcon(new ImageIcon(imageSmallSize));
                        }
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e)
            {
                mouseButtonDown = false;

                if (getBorder() != null)
                {
                    if (mouseEntered)
                    {
                        setBackground(new Color(229, 241, 251));
                        setBorder(new LineBorder(new Color(0, 120, 215), 1, true));
                    } else
                    {
                        setBackground(new Color(225, 225, 225));
                        setBorder(new LineBorder(new Color(173, 173, 173), 1, true));
                    }
                } else
                {
                    if (getIcon() != null)
                    {
                        if (imageOriginalSize == null)
                        {
                            InitialiseIcon();

                            if (mouseEntered)
                            {
                                setIcon(new ImageIcon(imageLargeSize));
                            }
                            else
                            {
                                setIcon(new ImageIcon(imageOriginalSize));
                            }
                        } else
                        {
                            if (mouseEntered)
                            {
                                setIcon(new ImageIcon(imageLargeSize));
                            }
                            else
                            {
                                setIcon(new ImageIcon(imageOriginalSize));
                            }
                        }
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e)
            {
                mouseEntered = true;

                if (getBorder() != null)
                {
                    if (mouseButtonDown)
                    {
                        setBackground(new Color(204, 228, 247));
                        setBorder(new LineBorder(new Color(0, 120, 215), 1, true));
                    } else
                    {
                        setBackground(new Color(229, 241, 251));
                        setBorder(new LineBorder(new Color(0, 120, 215), 1, true));
                    }
                } else
                {
                    if (getIcon() != null)
                    {
                        
                        if (imageOriginalSize == null)
                        {
                            InitialiseIcon();

                            if (mouseButtonDown)
                            {
                                setIcon(new ImageIcon(imageSmallSize));
                            }
                            else
                            {
                                setIcon(new ImageIcon(imageLargeSize));
                            }
                        } else
                        {
                            if (mouseButtonDown)
                            {
                                setIcon(new ImageIcon(imageSmallSize));
                            }
                            else
                            {
                                setIcon(new ImageIcon(imageLargeSize));
                            }
                        }
                    }
                }

            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                mouseEntered = false;

                if (getBorder() != null)
                {
                    setBackground(new Color(225, 225, 225));
                    setBorder(new LineBorder(new Color(173, 173, 173), 1, true));
                } else
                {
                    if (getIcon() != null)
                    {
                        if (imageOriginalSize == null)
                        {
                            InitialiseIcon();

                            setIcon(new ImageIcon(imageOriginalSize));

                        } else
                        {
                            setIcon(new ImageIcon(imageOriginalSize));
                        }
                    }
                }

            }
        });
    }

    private void InitialiseIcon()
    {
        ImageIcon originalIcon = (ImageIcon) getIcon();

        int imageOriginalWidth = originalIcon.getIconWidth();
        int imageOriginalHeight = originalIcon.getIconHeight();
        int imageSmallWidth = originalIcon.getIconWidth() - 5;
        int imageSmallHeight = originalIcon.getIconHeight() - 5;
        int imageLargeWidth = originalIcon.getIconWidth() + 2;
        int imageLargeHeight = originalIcon.getIconHeight() + 2;

        imageOriginalSize = new BufferedImage(imageOriginalWidth, imageOriginalHeight, BufferedImage.TYPE_INT_ARGB_PRE);
        imageSmallSize = new BufferedImage(imageSmallWidth, imageSmallHeight, BufferedImage.TYPE_INT_ARGB_PRE);
        imageLargeSize = new BufferedImage(imageLargeWidth, imageLargeHeight, BufferedImage.TYPE_INT_ARGB_PRE);
        
        Graphics2D imageOriginal = imageOriginalSize.createGraphics();
        Graphics2D imageSmall = imageSmallSize.createGraphics();
        Graphics2D imageLarge = imageLargeSize.createGraphics();

        imageOriginal.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        imageOriginal.drawImage(originalIcon.getImage(), 0, 0, originalIcon.getIconWidth(), originalIcon.getIconHeight(), null);

        imageSmall.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        imageSmall.drawImage(originalIcon.getImage(), 0, 0, imageSmallWidth, imageSmallHeight, null);

        imageLarge.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        imageLarge.drawImage(originalIcon.getImage(), 0, 0, imageLargeWidth, imageLargeHeight, null);

        imageOriginal.dispose();
        imageSmall.dispose();
        imageLarge.dispose();
    }
}
